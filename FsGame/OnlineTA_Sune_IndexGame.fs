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
          printfn "{ \"question\": \"let array1 = %A\nIndex %d = ?\", \"answer\": \"%d\"%s }" 
            array1 randIndex1 answer1 hint
  | 3 ->  let mutable array1 = Array2D.create 10 10 0
          for i in 0..9 do
            for j in 0..9 do
              array1.[i,j] <- (foo 1 9)
          let index2D1 = (foo 1 9)
          let index2D2 = (foo 1 9)
          printfn "{ \"question\": \"let array1 = \n%A\nIndex[%d,%d] = ?\", \"answer\": \"%d\"%s }" 
            array1 index2D1 index2D2 array1.[index2D1,index2D2] hint
  | 4 ->  let array1 = [| for i in 1 .. 5 -> (foo 1 9) |]
          let array2 = [| for i in 1 .. 5 -> (foo 1 9) |]
          let index1D1 = (foo 0 4)
          let index1D2 = (foo 0 4)
          let arrayResult = (array1.[index1D1]) + (array2.[index1D2])
          printfn "{ \"question\": \"let array1 = %A\nlet array2 = %A\nlet addition = array1.[%d]
 + array2.[%d]\naddition = ?\", \"answer\": \"%d\"%s }" array1 array2 index1D1 index1D2 arrayResult hint
  | 5 ->  let array1 = [| for i in 1 .. (foo 5 9) -> (foo 1 9) |]
          let randStep = (foo 1 4)
          let mutable count = 0
          for i in 0..randStep..(array1.Length - 1) do
            count <- count + array1.[i]
          printfn "{ \"question\": \"let array1 = %A\nlet mutable count = 0\nfor i in 0..%d..(array1.Length - 1) do
  count <- count + array1.[i]\ncount = ?\", \"answer\": \"%d\"%s }" array1 randStep count hint
  | _ ->  let array1 = [|0;1;2;3;4|]
          let rand1 = foo 0 4
          printfn "{ \"question\": \"let array1 = %A \nIndex %d = ?\", \"answer\": \"%A\"%s }"
            array1 rand1 array1.[rand1] hint

///Evaluates lemma against given answer versus calculated answer
let evalAnswer (question, answer) = 
  Console.Write((question:string) + " : " + (answer:string))

//Takes command line arguments and calls given function
[<EntryPoint>]
let main(args) =
  if args.Length < 1 then
    Console.Write("You need to give a function as an argument: getQuestion(x) or evalAnswer(question,answer)")
    -1
  else
    let funcType = args.[0]
    if funcType = "getQuestion" then
      if args.Length <> 2 then
        Console.Write("getQuestion needs the following argument: [difficulty level]")
      else
        let input1 = args.[1]
        match input1 |> Int32.TryParse with
        | true, input1 -> getQuestion(input1)
        | false, _ -> Console.WriteLine("This argument should be an integer")
    elif funcType = "evalAnswer" then
      if args.Length <> 3 then
        Console.Write("evalAnswer needs the following arguments: [question] [answer]")
      else
        let input1 = args.[1]
        let input2 = args.[2]
        evalAnswer(input1, input2)
    0