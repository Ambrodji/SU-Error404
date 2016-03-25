open System
#r "FsCheck.dll"
open FsCheck

/// Types that work with this program
type exp =
  | Plus of float * float
  | Sub of float * float
  | Mult of float * float
  | Div of float * float

///Generates a lemma
let getQuestion (x) = 
  //This function must return JSON string with the following parameters:
  Console.Write(x * 2)

///Evaluates lemma against given answer versus calculated answer
let evalAnswer (question, answer) = 
  //Code to evaluate the answer based on the question goes here
  Console.Write((question:string) + " : " + (answer:string))

///Takes command line arguments and calls given function
[<EntryPoint>]
let main(args) =
  if args.Length < 1 then
    Console.Write("You need to give a function as an argument: getQuestion() or evalAnswer(question,answer)")
    -1
  else
    let funcType = args.[0]
    if funcType = "getQuestion" then 
      let input1 = args.[1]
      getQuestion(input1)
    elif funcType = "evalAnswer" then
      if args.Length <> 3 then
        Console.Write("evalAnswer needs the following arguments: [question] [answer]")
      else
        let input1 = args.[1]
        let input2 = args.[2]
        evalAnswer(input1, input2)
    0