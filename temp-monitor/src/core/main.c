#include "temp-monitor/core/control_loop.h"
int main(int argc, char *argv[]) {
  ControlLoop ctrlLoop;
  int num_iters = 10000;
  double temp_treshold = 31.0;

  ControlLoop_init(&ctrlLoop, num_iters);
  ControlLoop_setTempThreshold(&ctrlLoop, temp_treshold);
  ControlLoop_startLoop(&ctrlLoop);

  return 0;
}
