#include "temp-monitor/core/cpu_temp.h"
#include <fstream>

double CpuTemperature::readTemperatureCelcius() {
  std::ifstream tempfile("/sys/class/thermal_zone0/temp");
  int temp_millideg;
  tempfile >> temp_millideg;
  double temp_c = temp_millideg / 1000.0;

  return temp_c;
}
