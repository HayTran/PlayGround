/* Create a WiFi access point and provide a web server on it. */

#include <ESP8266WiFi.h>
#include <WiFiClient.h> 
#include <ESP8266WebServer.h>

/* Set these to your desired credentials. */
const char *ssid = "Hay Tran";
const char *password = "hoinguoikeben";
WiFiServer server(4567);
WiFiClient client;

void setup() {
	Serial.begin(115200);
	initHardware();
  setupWiFi();
  server.begin();
}


void loop() 
{
    if (!client.connected()) {
        // try to connect to a new client
        client = server.available();
        digitalWrite(D0,HIGH);
    } else {
        byte data = 0;
        // read data from the connected client
        if (client.available() > 0) {
            data = client.read();
            Serial.println();
            Serial.print(data,DEC);
            server.write(data);
        }
        digitalWrite(D0,LOW);
    }
}
void setupWiFi()
{
  Serial.println();
  WiFi.mode(WIFI_AP);
  Serial.print("Configuring access point...");
  /* You can remove the password parameter if you want the AP to be open. */
  WiFi.softAP(ssid, password);
  IPAddress myIP = WiFi.softAPIP();
  Serial.print("AP IP address: ");
  Serial.println(myIP);
}

void initHardware()
{
  Serial.begin(115200);
  pinMode(D0,OUTPUT);
  digitalWrite(D0,HIGH);
}
