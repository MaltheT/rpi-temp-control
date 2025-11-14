#ifndef CONTROL_LOOP_H
#define CONTROL_LOOP_H

typedef struct {
  int num_iterations;
  double temp_threshold;
} ControlLoop;

void ControlLoop_init(ControlLoop *loop, int num_iters);
void ControlLoop_startLoop(ControlLoop *loop);
void ControlLoop_setTempThreshold(ControlLoop *loop, double threshold);

#endif
