int E1 = 6, M1 = 7, value = 255;
String data = "0", currentSubString = "", currentStatus = "idle";
bool dataAvailable = false;

void setup() {
  pinMode(M1, OUTPUT);   
  Serial.begin(9600);
}

void loop() {
  if(Serial.available() > 0){ //if data has been written to the Serial stream
    data = Serial.readString();
    Serial.println(data);

    if(data == "status") {
      getStatus();
    } else {
      int i = 0;
      while(i < data.length()) {
        currentSubString = data.substring(i,i+2);
        move(currentSubString);
        i = i+2;
      }
    }
  }
}

void move(String data) {
  Serial.println(data);
  String kaas = data.substring(0,1);
  int times = data.substring(1).toInt();
  
  if(kaas == "u") { //up
      up(times);
      Serial.println("up");
    } else if(kaas == "d") { //down
      down(times);
      Serial.println("down");
    } else if(kaas == "l") { //left
      left(times);
      Serial.println("left");
    } else if(kaas == "r") { //right
      right(times);
      Serial.println("right");
    } else if(kaas == "o") { //out
      right(times);
      Serial.println("out");
    } else if(kaas == "i") { //in
      right(times);
      Serial.println("in");
    } 
    delay(150);
}

void startTask() {
  // start met ophalen
}

void stopTask() {
  // stop met bewegen
}

void getStatus() {
  Serial.println(currentStatus);
}

void up(int i) {
  for(int j = 0; j < i; j++) {
    analogWrite(E1, value);
    digitalWrite(M1,HIGH); 
    delay(2000);
    analogWrite(E1, 0);
  }
}

void down(int i) {
  for(int j = 0; j < i; j++) {
    analogWrite(E1, value);
    digitalWrite(M1,LOW); 
    delay(2000);
    analogWrite(E1, 0);
  }
}

void left(int i) {
  for(int j = 0; j < i; j++) {
  
  }
}

void right(int i) {
  for(int j = 0; j < i; j++) {
  
  }
}




