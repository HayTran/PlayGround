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
}


void loop()
{
  if (!client.connected()) {
    // try to connect to a new client
    client = server.available();
    digitalWrite(D0, HIGH);
  } else {
    // read data from the connected client
    byte data = 0;
    if (client.available() > 0) {
      data = client.read();
      Serial.println();
      Serial.print(data, DEC);
      Serial.println();
      digitalWrite(D0, LOW);
    }
    if (data != -1) {
      Serial.print("Replying to client: ");
      Serial.println(data, DEC);
      client.write(data+3);
    }
  }

  Serial.print("Client status: ");
  Serial.println(client.status());
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
  /* Start TCP Server */
  server.begin();
}

void initHardware()
{
  Serial.begin(115200);
  pinMode(D0, OUTPUT);
  digitalWrite(D0, HIGH);
}
