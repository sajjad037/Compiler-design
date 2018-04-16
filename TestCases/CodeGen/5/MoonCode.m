          entry
          addi r14,r0,topaddr
          % processing: put()
          lw r1,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r1
          % link buffer to stack
          addi r1,r0, buf
          sw -12(r14),r1
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
          hlt
 
           % buffer space used for console output
buf       res 20
