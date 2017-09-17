/* Create a WiFi access point and provide a web server on it. */

#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <ESP8266WebServer.h>


/* Set these to your desired credentials. */
const char *ssid = "Hay Tran";
const char *password = "hoinguoikeben";

/* Set the number of client can connect*/
#define MAX_SRV_CLIENTS 3
WiFiServer server(4567);
WiFiClient serverClients[MAX_SRV_CLIENTS];

void setup() {
  Serial.begin(115200);
  initHardware();
  setupWiFi();
}


void loop()
{
  uint8_t i;
  if (server.hasClient()) {
    for (i = 0; i < MAX_SRV_CLIENTS; i++) {
      if (!serverClients[i] || !serverClients[i].connected()) {
        if (serverClients[i]) serverClients[i].stop();
        serverClients[i] = server.available();
        continue;
      }
    }
    //no free spot
    WiFiClient serverClient = server.available();
    serverClient.stop();
  }
  for (i = 0; i < MAX_SRV_CLIENTS; i++) {
    if (serverClients[i] && serverClients[i].connected()) {
      if (serverClients[i].available()) {
        while (serverClients[i].available()) {
          byte data = serverClients[i].read();
          Serial.println(data, DEC);
          Serial.print("Replying to client: ");
          Serial.println(data);
          serverClients[i].write(data);
        }
      }
    }
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
  /* Start TCP Server */
  server.begin();
}

void initHardware()
{
  Serial.begin(115200);
  pinMode(D0, OUTPUT);
  digitalWrite(D0, HIGH);
}
