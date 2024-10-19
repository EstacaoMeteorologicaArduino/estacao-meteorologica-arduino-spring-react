#include "Sensor.h"
#include "Network.h"
#include "Http.h"

extern TwoWire Wire1;
extern TwoWire Wire2;

Adafruit_BMP280 bmp(&Wire2);
DFRobot_SHT20 sht20(&Wire1, SHT20_I2C_ADDR);
LiquidCrystal_I2C lcd(0x27, 16, 2);

int amountOfRain = 0;

void setup() {

  initializeSensors(sht20, bmp, lcd);
  connectWifi(lcd);
}

void loop() {
  
  WiFiClient wiFiClient;
  HttpClient client = HttpClient(wiFiClient, SERVER, PORT);

  readRain(amountOfRain);
  float rain = amountOfRain * 0.2238; //Calibração do pluviômetro de báscula. Cada dasculada corresponde a 0.2238 mm
  float humidity = sht20.readHumidity();
  float temperature = sht20.readTemperature();
  float pressure = (bmp.readPressure() / 100); // Convertendo para hPa
  float altitude = bmp.readAltitude(1013.25); //Pressão atmosférica média no nível do mar

  postRequest(client, lcd, humidity, temperature, pressure, altitude, rain);

  delay(741UL);
  connectWifi(lcd);
}