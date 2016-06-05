//Matrix - Multiplication
//open System.Linq
open System

let Rnd = System.Random()

let Result x =
  match x with
    | 1 -> Rnd.Next(2 , 3)
    | 2 -> Rnd.Next(3 , 4)
    | 3 -> Rnd.Next(5 , 7)
    | 4 -> Rnd.Next(7 , 9)
    | 5 -> Rnd.Next(9 , 11)
    | _ -> failwith "LEL"



let M1 x =
  let mutable dance = Array2D.create (x) (x) 1.0
  let X = Array2D.length1 dance
  let Y = Array2D.length2 dance
  for i in 0..X-1 do
    for r in 0..Y-1 do
      let p = (float(Rnd.Next(1,10)))
      dance.[i,r] <- p
  dance

let M2 x =
  let mutable dance2 = Array2D.create (x) (x) 1.0
  let Xx = Array2D.length1 dance2
  let Yx = Array2D.length2 dance2
  for i in 0..Xx-1 do
    for r in 0..Yx-1 do
      let p = (float(Rnd.Next(1,10)))
      dance2.[i,r] <- p
  dance2



let MatrixProduct (A,B) =
  let fin = Array2D.create (Array2D.length1 A) (Array2D.length2 B) 0.0
  for r in 0..(Array2D.length1 A)-1  do
    for c in 0..(Array2D.length2 B)-1 do
      for o in 0..(Array2D.length1 A)-1 do
      fin.[r,c] <- fin.[r,c] + A.[r,o] * B.[o,c]
  fin


let Mega x =
  let k = Result x
  let ma1 = M1 k
  let ma2 = M2 k

  printfn "%s%A%s%A%s%A%s" ("{\"question\": \"") (ma1) ("\n") (ma2) ("\"\n, \"answer\": \"") (MatrixProduct(ma1,ma2)) ("\"}")

  //MatrixProduct (ma1,ma2) 

let getQuestion (x) = Mega x

////////////////////



let evalAnswer (question, answer) =
  Console.WriteLine("Not used")
  (*
  if (MatrixProduct (question)) = answer then
    System.Console.WriteLine(true)
  else
    System.Console.WriteLine(false)
    *)
//Takes command line arguments and calls given function
[<EntryPoint>]
let main(args) =
  if args.Length < 1 then
    System.Console.Write("You need to give a function as an argument: getQuestion(x) or evalAnswer(question,answer)")
    -1
  else
    let funcType = args.[0]
    if funcType = "getQuestion" then
      let input1 = args.[1]
      getQuestion(int(input1))
      ()
    elif funcType = "evalAnswer" then
      if args.Length <> 3 then
        System.Console.Write("evalAnswer needs the following arguments: [question] [answer]")
      else
        let input1 = args.[1]
        let input2 = args.[2]
        evalAnswer(input1, input2)
    0

