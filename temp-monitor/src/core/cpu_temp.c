#include "temp-monitor/core/cpu_temp.h"
#include <stdio.h>

double readTemperatureCelcius(void) {
  FILE *tempfile = fopen("/sys/class/thermal/thermal_zone0/temp", "r");

  if (!tempfile) {
    printf("error reading the temperature file\n");
    return -1.0;
  }

  int temp_millideg = 0;
  fscanf(tempfile, "%d", &temp_millideg);
  fclose(tempfile);

  double temp_c = temp_millideg / 1000.0;
  return temp_c;
}
