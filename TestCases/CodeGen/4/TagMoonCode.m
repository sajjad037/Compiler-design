           entry
           addi r14,r0,topaddr
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           hlt
 
 f1         res 4
           % buffer space used for console output
buf        res 20
