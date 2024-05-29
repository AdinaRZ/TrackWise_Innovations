import cv2
import pytesseract
import time
import gpiozero
import mysql.connector
import re
import datetime
import threading

button0=gpiozero.Button(3)
led0=gpiozero.LED(17)
led1=gpiozero.LED(4)
led2=gpiozero.LED(12)

camera=cv2.VideoCapture(0)
fgbg=cv2.createBackgroundSubtractorMOG2()

masini_parcate=0
numar_locuri=0
patern0=r'\b[A-Z]{2}\d{2}[A-Z]{3}\b|\b[A-Z]{2} \d{2} [A-Z]{3}\b|\b[A-Z]{2}\d{6}\b|\b[A-Z]{2} \d{6}\b|\b[B]\d{3}[A-Z]{3}\b|\b[B] \d{3} [A-Z]{3}\b'

class Lacat:
    def __init__(self):
        self.semaphore = threading.Semaphore(value=1) 

    def blocheaza(self):
        self.semaphore.acquire()  

    def deschide(self):
        self.semaphore.release() 

connection=mysql.connector.connect(
    host="35.197.71.26",
    user="balet",
    password="balet",
    database="ip")

cursor=connection.cursor()

def prima_interogare():
    query = "SELECT numar_locuri FROM locuri_parcare WHERE id_loc_parcare = 1"
    cursor.execute(query)

    result = cursor.fetchone()

    if result:
        return result[0]
    else:
        raise Exception("Nu a fost găsită nicio înregistrare pentru ID-ul specificat.")

def get_max_parking_spots(modifier):
    
    query = "UPDATE locuri_parcare SET numar_locuri = %s WHERE id_loc_parcare = 1"
    cursor.execute(query, (modifier,))
    query = "SELECT numar_locuri FROM locuri_parcare WHERE id_loc_parcare = 1"
    cursor.execute(query)

    result = cursor.fetchone()
    connection.commit()
    if result:
        return result[0]
    else:
        raise Exception("Numărul de locuri a fost actualizat cu succes!")
    
def intrare():
    lacat.blocheaza()
    global masini_parcate
    global numar_locuri
    print("!!!!")
    ret,frame=camera.read()         #face o poza noua
    frame_gray=cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)    #face imaginea gri
    edge=cv2.Canny(frame_gray,100,200)       #identifica marginile din imagine
    file_name="image.jpg" 
    cv2.imwrite(file_name,edge)
    print("Cautare numar inmatriculare")
    text = pytesseract.image_to_string("image.jpg")      #extrage textul din poza facuta
    print(text)
    if re.findall(patern0, text):
        if numar_locuri !=0 :
            match_text=re.findall(patern0, text)[0].strip("[']").replace(" ","")
            cautare_db=match_text
            query="SELECT * FROM utilizatori WHERE nr_inmatriculare=%s"
            cursor.execute(query,(cautare_db,))
            result=cursor.fetchone()
            if result:
                print('Se permite accesul in parcare!')
                cv2.destroyAllWindows()
                led0.on()
                data_intrare=datetime.datetime.now().strftime('%Y-%m-%d')
                ora_intrare=datetime.datetime.now().strftime('%H:%M:%S')
                cursor.execute('INSERT INTO Parcare (NumarInmatriculare, DataIntrare, OraIntrare)VALUES (%s, %s, %s)', (match_text, data_intrare, ora_intrare))
                connection.commit()
                numar_locuri=get_max_parking_spots(numar_locuri-1)
                masini_parcate=masini_parcate+1
                print(numar_locuri)
                time.sleep(7)
                led0.off()
            else:
                print('Numarul nu exista in database!')
                led1.on()
                time.sleep(7)
                led1.off()
        else:
            print('Nu exista locuri liber!')
            led1.on()
            time.sleep(7)
            led1.off()
    lacat.deschide()


def buton_iesire():
    lacat.blocheaza()
    global masini_parcate
    global numar_locuri
    if masini_parcate == 0:
        print("parcarea este goala!!!")
    else:
        led0.on()
        print("iesire parcare")
        numar_locuri=get_max_parking_spots(numar_locuri+1)
        masini_parcate=masini_parcate-1
        time.sleep(7)
        led0.off()
    lacat.deschide()

def button_handler():
    button0.when_pressed=buton_iesire

button_thread = threading.Thread(target=button_handler)
button_thread.start()

led2.on()
lacat = Lacat()
numar_locuri = prima_interogare()
bd=numar_locuri
print(numar_locuri)

while True:      #un while infinit
    ret,frame=camera.read()    #face o poza
    fgmask=fgbg.apply(frame)   #se seteaza modulul de motion detection
    contours, _ = cv2.findContours(fgmask, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)    #se cauta cat din imagine se modifica si unde
    for contour in contours:
        if cv2.contourArea(contour)>75000:     #verifica daca motion-ul este destul de mare
            intrare()
    cv2.imshow('camera video',frame)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break
    
camera.release()
cv2.destroyAllWindows()    
cursor.execute("UPDATE locuri_parcare SET numar_locuri = %s WHERE id_loc_parcare = 1",(bd,))
connection.commit()
print('program incheiat')
led2.off()  
button_thread.join()