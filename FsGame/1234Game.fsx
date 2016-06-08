//Matrix - Multiplication
//open System.Linq
#load "JsonCheckFinal.fsx"
open JSONTest
open System

let Rnd = System.Random()

let convertToString (l: 'a seq) = "[|" + System.String.Join("; ", l) + "|]"

let flatten (A: 'a[,]) = A |> Seq.cast<'a>

let getRow r (A:_[,]) =
    flatten A.[r..r,*] |> Seq.toArray  

let flattenToString (A: 'a[,]) =
    let mutable str = ""
    for i in 0..A.GetLength(0)-2 do
      str <- str + convertToString (getRow i A) + ";\\n"
    str <- "[|" + str + (convertToString (getRow (A.GetLength(0)-1) (A))) + "|]"
    str

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

  ("{ \"question\": \"") + (flattenToString ma1) + ("\\n\\n") + (flattenToString ma2)
   + ("\", \"answer\": \"") + (string(MatrixProduct(ma1,ma2))) + ("\", \"hint\": \"Go at it\" }")

  //MatrixProduct (ma1,ma2) 

let getQuestion (x) = Mega x

////////////////////



let evalAnswer (question, answer) =
  let mutable boolVal = true
  if question <> answer then boolVal <- not boolVal
  boolVal

/// <summary>Takes command line arguments and calls the given functions</summary>
/// <returns>Unit</returns>
[<EntryPoint>]
let main(args) =
  if args.Length < 1 then
    printfn("You need to give a function as an argument: getQuestion(x) or evalAnswer(question,answer)")
    -1
  else
    let funcType = args.[0]
    if funcType = "getQuestion" then
      if args.Length <> 2 then
        printfn("getQuestion needs the following argument: [difficulty level]")
      else
        let input1 = args.[1]
        match input1 |> System.Int32.TryParse with
        | true, input1 -> printfn "%s" (getQuestion(input1))
        | false, _ -> printfn ("This argument should be an integer")
    elif funcType = "evalAnswer" then
      if args.Length <> 3 then
        printfn("evalAnswer needs the following arguments: [question] [answer]")
      else
        let input1 = args.[1]
        let input2 = args.[2]
        printfn "%b" (evalAnswer(input1, input2))
    elif funcType = "debug" then
      if args.Length <> 2 then
        printfn("debug needs the following arguments: [number of runs]")
      else
        let input1 = args.[1]
        match input1 |> System.Int32.TryParse with
        | true, input1 -> 
          for i in 1..int(input1) do
            testIterationQuestion (getQuestion(i), (i))  
          printfn "   -----\n"
          testfn (evalAnswer("1", "1") = true) (evalAnswer("1", "1"))
          testfn (evalAnswer("1", "2") = false) (evalAnswer("1", "2"))
        | false, _ -> printfn ("This argument should be an integer")
    0
