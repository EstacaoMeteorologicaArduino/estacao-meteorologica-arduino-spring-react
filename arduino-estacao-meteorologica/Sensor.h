#include <DFRobot_SHT20.h>
#include <LiquidCrystal_I2C.h>
#include <Adafruit_Sensor.h>
#include <Adafruit_BMP280.h>
#include "temperature.h"

#define RAIN_SENSOR_PORT 2

void initializeSensors(DFRobot_SHT20 sht20, Adafruit_BMP280 &bmp, LiquidCrystal_I2C &lcd) {

  lcd.begin();
  lcd.backlight();
  lcd.clear();

  lcd.setCursor(0, 0);
  lcd.print("INICIALIZANDO");
  lcd.setCursor(0, 1);
  lcd.print("SENSORES...");

  sht20.initSHT20();

  pinMode(RAIN_SENSOR_PORT, INPUT_PULLUP);

  if (!bmp.begin(0x76)) {

    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("SENSOR BMP280");
    lcd.setCursor(0, 1);
    lcd.print("NÃO DETECTADO...");
    while (1)
      ;
  }

  delay(3000UL);
}

void readRain(int &amountOfRain) {

  amountOfRain = 0;
  int oldStatus = 0;
  int initialStatus = 0;

  for (int i = 0; i < 1180; i++) {

    initialStatus = digitalRead(RAIN_SENSOR_PORT);

    if ((initialStatus == LOW) && (oldStatus == HIGH)) {

      amountOfRain++;
    }

    oldStatus = initialStatus;
    delay(50UL);
  }
}