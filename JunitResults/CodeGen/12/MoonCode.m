          entry
          addi r14,r0,topaddr
          % processing:  := 
          lw r2,0(r14)
          sw 0(r14),r2
          % processing:  := 
          lw r2,0(r14)
          sw 0(r14),r2
          % processing: t1 :=  + 
          lw r2,0(r14)
          lw r3,0(r14)
          add r4,r2,r3
          sw 0(r14),r4
          % processing:  := t1
          lw r3,0(r14)
          sw 0(r14),r3
          % processing: t2 :=  + 
          lw r3,0(r14)
          lw r4,0(r14)
          add r1,r3,r4
          sw 0(r14),r1
          % processing:  := t2
          lw r4,0(r14)
          sw 0(r14),r4
          % processing: t3 :=  * 
          lw r4,0(r14)
          lw r1,0(r14)
          mul r2,r4,r1
          sw 0(r14),r2
          % processing:  := t3
          lw r1,0(r14)
          sw 0(r14),r1
          % processing: t4 :=  * 
          lw r1,0(r14)
          lw r2,0(r14)
          mul r3,r1,r2
          sw 0(r14),r3
          % processing:  := t4
          lw r2,0(r14)
          sw 0(r14),r2
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
          % processing: put()
          lw r2,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r2
          % link buffer to stack
          addi r2,r0, buf
          sw -12(r14),r2
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
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
          % processing: put()
          lw r2,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r2
          % link buffer to stack
          addi r2,r0, buf
          sw -12(r14),r2
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
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
          % processing: put()
          lw r2,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r2
          % link buffer to stack
          addi r2,r0, buf
          sw -12(r14),r2
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
          % processing:  := 
          lw r2,0(r14)
          sw 0(r14),r2
          % processing: put()
          lw r3,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r3
          % link buffer to stack
          addi r3,r0, buf
          sw -12(r14),r3
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
          % processing:  := 
          lw r3,0(r14)
          sw 0(r14),r3
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
          % processing:  := 
          lw r1,0(r14)
          sw 0(r14),r1
          % processing:  := 
          lw r1,0(r14)
          sw 0(r14),r1
          % processing: t5 :=  + 
          lw r1,0(r14)
          lw r2,0(r14)
          add r4,r1,r2
          sw 0(r14),r4
          % processing:  := t5
          lw r2,0(r14)
          sw 0(r14),r2
          % processing: t6 :=  * 
          lw r2,0(r14)
          lw r4,0(r14)
          mul r3,r2,r4
          sw 0(r14),r3
          % processing:  := t6
          lw r4,0(r14)
          sw 0(r14),r4
          % processing:  := 
          lw r4,0(r14)
          sw 0(r14),r4
          % processing:  := 
          lw r4,0(r14)
          sw 0(r14),r4
          % processing: put()
          lw r2,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r2
          % link buffer to stack
          addi r2,r0, buf
          sw -12(r14),r2
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
          % processing: put()
          lw r4,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r4
          % link buffer to stack
          addi r4,r0, buf
          sw -12(r14),r4
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
          % processing: put()
          lw r2,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r2
          % link buffer to stack
          addi r2,r0, buf
          sw -12(r14),r2
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
          % processing: put()
          lw r4,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r4
          % link buffer to stack
          addi r4,r0, buf
          sw -12(r14),r4
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
          % processing: put()
          lw r2,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r2
          % link buffer to stack
          addi r2,r0, buf
          sw -12(r14),r2
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
          % processing: put()
          lw r4,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r4
          % link buffer to stack
          addi r4,r0, buf
          sw -12(r14),r4
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
          % processing: put()
          lw r2,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r2
          % link buffer to stack
          addi r2,r0, buf
          sw -12(r14),r2
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
          % processing: put()
          lw r4,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r4
          % link buffer to stack
          addi r4,r0, buf
          sw -12(r14),r4
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
          % processing: put()
          lw r2,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r2
          % link buffer to stack
          addi r2,r0, buf
          sw -12(r14),r2
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
          % processing: put()
          lw r4,0(r14)
          % put value on stack
          addi r14,r14,0
          sw -8(r14),r4
          % link buffer to stack
          addi r4,r0, buf
          sw -12(r14),r4
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,0
          hlt
 
           % buffer space used for console output
buf       res 20
