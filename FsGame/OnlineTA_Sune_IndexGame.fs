open System

let rnd = System.Random()

/// Return a random bool from minNum to maxNum
let foo minNum maxNum = 
    let first = rnd.Next(minNum, maxNum)
    first

let randArit () =
  let first = (foo 1 5)

///Generates a lemma
let getQuestion (x:int) = 
  match x with
  | 1 -> let array1 = [|0;1;2;3;4|]
         printfn "%A" array1
         let rand1 = foo 0 5
         printfn "%s %A %s" ("\"question\": \"") array1 ("\nIndex " + string(rand1) + " = ?\"")
  
  | 2 -> let cake = [| for i in 1 .. 10 -> (foo 1 10) * (foo 1 10) |]
         printfn "%s %A %s" ("\"question\": \"") cake ("\nIndex " + string(foo 0 (cake.Length)) + " = ?\"")
  
  | 3 -> let mutable array3 = Array2D.create 10 10 0
         for i in 0..9 do
            for j in 0..9 do
              array3.[i,j] <- (foo 1 10)
         let index2D1 = (foo 1 10)
         let index2D2 = (foo 1 10)
         printfn "%s %A %s" ("\"question\": \"") array3 ("\nIndex [" + string(index2D1) + "," + string(index2D2) + "] = ?\"")
  | 4 -> let array1 = [| for i in 1 .. 5 -> (foo 1 10) |]
         let array2 = [| for i in 1 .. 5 -> (foo 1 10) |]

         let index1D1 = (foo 0 5)
         let index1D2 = (foo 0 5)
         let arrayResult = (array1.[index1D1]) + (array2.[index1D2])
         printfn "%A" array1
         printfn "%A" array2
         printfn "%s %d" ("Array1 index " + string(index1D1) + " + Array2 index " + string(index1D2) + " = ") arrayResult
  | _ -> Console.WriteLine("")

///Evaluates lemma against given answer versus calculated answer
let evalAnswer (question, answer) = 
  Console.Write((question:string) + " : " + (answer:string))

//Takes command line arguments and calls given function
[<EntryPoint>]
let main(args) =
  if args.Length < 1 then
    Console.Write("You need to give a function as an argument: getQuestion() or evalAnswer(question,answer)")
    -1
  else
    let funcType = args.[0]
    if funcType = "getQuestion" then 
      let input1 = args.[1]
      getQuestion(int(input1))
    elif funcType = "evalAnswer" then
      if args.Length <> 3 then
        Console.Write("evalAnswer needs the following arguments: [question] [answer]")
      else
        let input1 = args.[1]
        let input2 = args.[2]
        evalAnswer(input1, input2)
    0