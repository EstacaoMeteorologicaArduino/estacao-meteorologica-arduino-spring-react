#include <ArduinoJson.h>
#include <ArduinoHttpClient.h>

#define SERVER "" //URL do backend
#define PORT 8180 //Porta do backend

void postRequest(HttpClient client, LiquidCrystal_I2C &lcd, float humidity, float temperature, float pressure, float altitude, float rain) {

  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("ENVIANDO P/ SER.");

  JsonDocument request;
  request[String("temperatura")] = temperature;
  request[String("umidade")] = humidity;
  request[String("pressao")] = pressure;
  request[String("altitude")] = altitude;
  request[String("chuva")] = rain;

  String jsonRequest;
  serializeJson(request, jsonRequest);

  client.post("/v1/estacao-meteorologica", "application/json", jsonRequest);

  int statusCode = client.responseStatusCode();

  lcd.setCursor(0, 1);
  lcd.print("HTTP STATUS: ");
  lcd.setCursor(13, 1);
  lcd.print(statusCode);
}