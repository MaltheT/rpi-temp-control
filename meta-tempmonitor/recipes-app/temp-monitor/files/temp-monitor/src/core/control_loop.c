#define _POSIX_C_SOURCE 200809L
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
  const useconds_t max_delay = 500000;
  const useconds_t min_delay = 50000;

  for (int i = 0; i < loop->num_iterations; i++) {
    double temp_c = readTemperatureCelcius();
    printf("%.2f\n", temp_c);

    useconds_t delay = max_delay - (useconds_t)(temp_c * 5000);
    if (delay < min_delay)
      delay = min_delay;

    turnOnLed();
    usleep(delay);
    turnOffLed();
    usleep(delay);
  }
}

void ControlLoop_setTempThreshold(ControlLoop *loop, double thresh) {
  loop->temp_threshold = thresh;
}
