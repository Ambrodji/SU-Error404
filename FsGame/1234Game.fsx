//Matrix - Multiplication
//open System.Linq

let Rnd = System.Random()


let mutable dance = Array2D.create 2 2 1.0
let X = Array2D.length1 dance
let Y = Array2D.length2 dance
for i in 0..X-1 do
  for r in 0..Y-1 do
    let p = (float(Rnd.Next(1,10)))
    dance.[i,r] <- p

let mutable dance2 = Array2D.create 2 2 1.0
let Xx = Array2D.length1 dance2
let Yx = Array2D.length2 dance2
for i in 0..Xx-1 do
  for r in 0..Yx-1 do
    let p = (float(Rnd.Next(1,10)))
    dance2.[i,r] <- p

let MatrixProduct =
  let fin = Array2D.copy dance
  for r in 0..(Array2D.length1 dance)-1 do
    for c in 0..(Array2D.length2 dance2)-1 do
      for o in 0..(Array2D.length1 dance)-1 do
        //We need 3 loops to make a proper matrixproduct
      fin.[r,c] <- dance.[r,c] + dance.[r,o] * dance2.[o,c]



printfn "%A : 1" (dance)
printfn "%A : 2" (dance2)

printfn "%A : fin" (MatrixProduct)
