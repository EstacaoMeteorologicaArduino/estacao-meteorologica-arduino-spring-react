#include <WiFi.h>

#define SECRET_SSID "" //Nome da rede Wifi
#define SECRET_PASS "" //Senha da rede Wifi

void printWifiStatus(LiquidCrystal_I2C lcd) {

  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("REDE: ");
  lcd.setCursor(6, 0);
  lcd.print(WiFi.SSID());

  lcd.setCursor(0, 1);
  lcd.print(WiFi.localIP());

  delay(2000UL);
}

void connectWifi(LiquidCrystal_I2C &lcd) {

  if (WiFi.status() != WL_CONNECTED) {

    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("SEM CONEXAO WIFI");

    if (WiFi.status() == WL_NO_SHIELD) {

      lcd.setCursor(0, 1);
      lcd.print("FALHA MOD. WIFI");
      while (true)
        ;
    }

    int statusWifi = WL_IDLE_STATUS;

    while (statusWifi != WL_CONNECTED) {

      lcd.setCursor(0, 1);
      lcd.print("CONECTANDO...");

      statusWifi = WiFi.begin(SECRET_SSID, SECRET_PASS);

      delay(3000);
    }
    printWifiStatus(lcd);
  }
}