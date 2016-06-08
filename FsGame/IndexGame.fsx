#load "JsonCheckFinal.fsx"
open JSONTest
open System

/// OnlineTA
///
/// How to compile:
/// <code>
/// fsharpc OnlineTA_Sune_IndexGame.fs
/// </code>
///
/// Author: Error404
/// Date: 2016/05/15

let rnd = System.Random()

let ConvertToString (l: 'a seq) = "[|" + System.String.Join("; ", l) + "|]"
//let ConvertToString1 (l: 'a seq) = for i in 0..l.Lenght-1 do ("[|" + System.String.Join("; ", l) + "|]")

/// Return a random bool from minNum to maxNum
let foo minNum maxNum = 
    //foo 1 10, means numbers from 1 to 10
    let first = rnd.Next(minNum, maxNum+1)
    first

let hint = ", \"hint\": \"Answer the question on the last line\""

///Generates a lemma
let getQuestion (x) = 
  match x with
  | 2 ->  let array1 = [| for i in 1 .. 10 -> (foo 1 9) * (foo 1 9) |]
          let randIndex1 = foo 0 (array1.Length-1)
          let answer1 = array1.[randIndex1]
          let op = ConvertToString array1
          ("{ \"question\": \"let array1 =") + ConvertToString array1 + ("\\nIndex") + string(randIndex1) 
          + (" = ?\", \"answer\": \"") + string(answer1) + ("\"") + hint + (" }")
  | 3 ->  let mutable array1 = Array2D.create 10 10 0
          for i in 0..9 do
            for j in 0..9 do
              array1.[i,j] <- (foo 1 9)
          let index2D1 = (foo 1 9)
          let index2D2 = (foo 1 9)
          ("{ \"question\": \"let array1 = \\n") + array1.ToString() + ("\\nIndex[") + string(index2D1)
          + (",") + string(index2D2) + ("] = ?\", \"answer\": \"") + string(array1.[index2D1,index2D2]) + ("\"") + hint + (" }")
  | 4 ->  let array1 = [| for i in 1 .. 5 -> (foo 1 9) |]
          let array2 = [| for i in 1 .. 5 -> (foo 1 9) |]
          let index1D1 = (foo 0 4)
          let index1D2 = (foo 0 4)
          let arrayResult = (array1.[index1D1]) + (array2.[index1D2])
          ("{ \"question\": \"let array1 = ") + ConvertToString array1 + ("\\nlet array2 = ") + ConvertToString array2 + ("\\nlet addition = ")
           + string(array1.[index1D1]) + (" + array2.[string(index1D2)]\\naddition = ?\", \"answer\": \"")
           + string(arrayResult) + ("\"") + hint + (" }")     
  | 5 ->  let array1 = [| for i in 1 .. (foo 5 9) -> (foo 1 9) |]
          let randStep = (foo 1 4)
          let mutable count = 0
          for i in 0..randStep..(array1.Length - 1) do
            count <- count + array1.[i]
          ("{ \"question\": \"let array1 = ") + ConvertToString array1 + ("\\nlet mutable count = 0\\nfor i in 0..")
           + string(randStep) + ("..(array1.Length - 1) do  count <- count + array1.[i]\\ncount = ?\", \"answer\": \"")
           + string(count) + ("\"") + hint + (" }")
  | _ ->  let array1 = [|0;1;2;3;4|]
          let rand1 = foo 0 4
          ("{ \"question\": \"let array1 = ") + string(array1) + ("\\nIndex ") + string(rand1)
           + (" = ?\", \"answer\": \"") + string(array1.[rand1]) + ("\"") + hint + (" }")


///Evaluates lemma against given answer versus calculated answer
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