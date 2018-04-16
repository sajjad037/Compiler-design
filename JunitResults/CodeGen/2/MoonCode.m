          entry
          addi r14,r0,topaddr
          % processing:  := 
          lw r2,0(r14)
          sw 0(r14),r2
          % processing:  := 
          lw r2,0(r14)
          sw 0(r14),r2
          % processing:  := 
          lw r2,0(r14)
          sw 0(r14),r2
          % processing:  := 
          lw r2,0(r14)
          sw 0(r14),r2
          % processing:  := 
          lw r2,0(r14)
          sw 0(r14),r2
          % processing:  := 
          lw r2,0(r14)
          sw 0(r14),r2
          % processing:  := 
          lw r2,0(r14)
          sw 0(r14),r2
          % processing:  := 
          lw r2,0(r14)
          sw 0(r14),r2
          hlt
 
           % buffer space used for console output
buf       res 20
