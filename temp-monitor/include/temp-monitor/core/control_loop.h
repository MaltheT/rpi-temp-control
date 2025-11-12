#pragma once

#include "temp-monitor/core/cpu_temp.h"
#include "temp-monitor/core/led_gpio.h"
class ControlLoop {

private:
  int num_iterations;
  double temp_threshold;
  CpuTemperature cpu_temp;
  LedGpio led_gpio;

public:
  ControlLoop(int num_iters);

  void startLoop();
  void setTempThreshold(double threshold);
};
