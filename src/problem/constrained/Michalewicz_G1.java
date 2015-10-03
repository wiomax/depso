/**
 * @Description: Benchmark function (Michalewicz's G1).
 * X*(Optimal point):
 * X1~X9, X13: 1
 * X10~X12: 3
 * Y*(Optimal value)
 * Y1=15
 *  
 * @Principles: 
 *   1) each constrained problem instance is extended from problem.ProblemEncoder.	
 *   2) In the constructor function (i.e., the function with the class name), three
 *      parts should be implemented: 
 *      a) call super(NX, NY), in which NX is the number of variables (or parameters), and NY 
 *         is the total number of response models (objectives and constraints); 
 *      b) set range for each parameter by using the function setDefaultXAt(i, LB_P, UB_P), 
 *         in which i means the i-th parameter, LB_P and UB_P are the lower and upper bounds;
 *      c) set range for each response model (objective or constraint) by using the function 
 *         setDefaultXAt(i, LB_R, UB_R), in which i means the i-th parameter, LB_R and UB_R 
 *         are the lower and upper bounds: if both LB_R and UB_R are MINBOUND, then
 *         the response model is a minimization objective; if both LB_R and UB_R are MAXBOUND, 
 *         then the response model is a maximization objective; if LB_R = MINBOUND, then
 *         the response model is a lessthan constraint; if UB_R = MAXBOUND, then
 *         the response model is a largerthan constraint.
 *    3) Realize the function: "double calcTargetAt(int index, double[] VX)"
 *       in which "index" is the index of a response model, and "VX" is an array of parameter values.
 * 
 * @ Author        Create/Modi     Note
 * Xiaofeng Xie    Dec 28, 2001
 * Xiaofeng Xie    Mar 01, 2003
 * Xiaofeng Xie    May 11, 2004
 *
 * @URL: http://www.wiomax.com/nop/
 * @Email: xie@wiomax.com, xfxie@alumni.cmu.edu
 *
 * @version 1.0
 * @Since MAOS1.0
 *
 * @References:
 * [1] Floundas C, Pardalos P. A Collection of Test Problems for Constrained
 * Global Optimization. Springer-Verlag, LNCS, 1987, 455
 * [2] Koziel S, Michalewicz Z. Evolutionary algorithms, homomorphous
 * mappings, and constrained parameter optimization. Evolutionary Computation,
 * 1999, 7: 19-44
 * [3] X F Xie, W J Zhang. SWAF: Swarm algorithm framework for numerical 
 * optimization. Genetic and Evolutionary Computation Conference (GECCO), 
 * Seattle, WA, USA, 2004: 238-250
 */

package problem.constrained;

import problem.ProblemEncoder;

// Principle #1: each constrained problem instance is extended from problem.ProblemEncoder
public class Michalewicz_G1 extends ProblemEncoder {

  	
  public Michalewicz_G1() throws Exception {
    //Principle #2a: call super(NX, NY), in which NX is the number of variables, and NY 
    //is the total number of response functions (objectives and constraints)
    super(13, 10); //13: number of varibles; 10: number of objectives and constraints
    
    //Principle #2b: set range for each parameter by using the function setDefaultXAt(i, LB_P, UB_P),
    //in which i means the i-th parameter, LB_P and UB_P are the lower and upper bounds
    for(int i=0; i<9; i++) {
      setDefaultXAt(i, 0, 1);     //Parameter range: [0, 1]
    }
    for(int i=9; i<12; i++) {
      setDefaultXAt(i, 0, 100);   //Parameter range: [0, 100]
    }
    setDefaultXAt(12, 0, 1);      //Parameter range: [0, 1]

    //Principle #2c: set range for each response model (objective or constraint), by using the function 
    //setDefaultYAt(i, LB_R, UB_R), , in which i means the i-th parameter, LB_R and UB_R 
    //are the lower and upper bounds: if both LB_R and UB_R are MINBOUND, then
    //the response model is a minimization objective; if both LB_R and UB_R are MAXBOUND, 
    //then the response model is a maximization objective; if LB_R = MINBOUND, then
    //the response model is a lessthan constraint; if UB_R = MAXBOUND, then
    //the response model is a largerthan constraint.

    setDefaultYAt(0, MINBOUND, MINBOUND); // Minimize Objective
    for(int i=1; i<4; i++) {
      setDefaultYAt(i, MINBOUND, 10); //Lessthan constraints (<10)
    }
    for(int i=4; i<10; i++) {
      setDefaultYAt(i, MINBOUND, 0);   //Lessthan constraints (<0)
    }
  }

  // Principle #3: Realize the function: "double calcTargetAt(int index, double[] VX)"
  // in which "index" is the index of a response model, and "VX" is an array of parameter values.
  // The function return a value for the given VX array.
  //
  // Each case is a response model (objective or constraint). 
  // Here case 0 is the objective function, and other 9 cases are constraint functions.
  protected double calcTargetAt(int index, double[] VX) {
    double value = 0;
    switch(index) {
    case 0:
      value = 5*(VX[0]+VX[1]+VX[2]+VX[3])-5*(VX[0]*VX[0]+VX[1]*VX[1]+VX[2]*VX[2]+VX[3]*VX[3])-(VX[4]+VX[5]+VX[6]+VX[7]+VX[8]+VX[9]+VX[10]+VX[11]+VX[12]);
      break;
    case 1:
      value = 2*VX[0]+2*VX[1]+VX[9]+VX[10]-10;
      break;
    case 2:
      value = 2*VX[0]+2*VX[2]+VX[9]+VX[11]-10;
      break;
    case 3:
      value = 2*VX[1]+2*VX[2]+VX[10]+VX[11]-10;
      break;
    case 4:
      value = -8*VX[0]+VX[9];
      break;
    case 5:
      value = -8*VX[1]+VX[10];
      break;
    case 6:
      value = -8*VX[2]+VX[11];
      break;
    case 7:
      value = -2*VX[3]-VX[4]+VX[9];
      break;
    case 8:
      value = -2*VX[5]-VX[6]+VX[10];
      break;
    case 9:
      value = -2*VX[7]-VX[8]+VX[11];
      break;
    default:
      return Double.NaN;
    }
    return value;
  }
}


