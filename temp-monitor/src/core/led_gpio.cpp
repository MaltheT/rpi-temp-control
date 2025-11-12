#include <fstream>
#include <temp-monitor/core/led_gpio.h>

void LedGpio::turnOffLed() {
  std::ofstream led("sys/class/leds/ACT/brightness");
  led << "1";
  led.flush();
  return;
}

void LedGpio::turnOnLed() {
  std::ofstream led("sys/class/leds/ACT/brightness");
  led << "0";
  led.flush();
  return;
}
