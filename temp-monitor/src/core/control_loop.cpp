#include "temp-monitor/core/control_loop.h"
#include "temp-monitor/core/cpu_temp.h"
#include "temp-monitor/core/led_gpio.h"

ControlLoop::ControlLoop(int num_iters) { num_iterations = num_iters; }

void ControlLoop::startLoop() {
  for (int i = 0; i < num_iterations; i++) {
    float temp_c = cpu_temp.readTemperatureCelcius();
    if (temp_c > temp_threshold) {
      led_gpio.turnOnLed();
    } else {
      led_gpio.turnOffLed();
    }
  }
}

void ControlLoop::setTempThreshold(double thresh) { temp_threshold = thresh; }
