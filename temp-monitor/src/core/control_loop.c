#include "temp-monitor/core/control_loop.h"
#include "temp-monitor/core/cpu_temp.h"
#include "temp-monitor/core/led_control.h"
#include <stdio.h>
#include <unistd.h>

void ControlLoop_init(ControlLoop *loop, int num_iters) {
  loop->num_iterations = num_iters;
  loop->temp_threshold = 0.0;
}

void ControlLoop_startLoop(ControlLoop *loop) {
  for (int i = 0; i < loop->num_iterations; i++) {
    double temp_c = readTemperatureCelcius();
    printf("%.2f\n", temp_c);
    sleep(temp_c / 100);
    turnOnLed();
    sleep(temp_c / 100);
    turnOffLed();
  }
}

void ControlLoop_setTempThreshold(ControlLoop *loop, double thresh) {
  loop->temp_threshold = thresh;
}
