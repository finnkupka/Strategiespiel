from firebase import firebase

firebase = firebase.FirebaseApplication("https://maxhome-f0b80.firebaseio.com/")
result = firebase.get("/led", None)
state = result["state"]

file = open("Resources/firebase_data.txt", "w")
file.write(state)
file.close()
