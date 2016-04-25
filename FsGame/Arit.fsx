module AritGame

let a = System.Random()

type Difficulty (Diff:int) = class
  let mutable StringCollector = ""

  member x.Pseudo() = a.Next(1, 100).ToString()
  
  member x.Numeric() =
    let b = a.Next(1, 3)
    let c = a.Next(1, 5)
    
    if Diff <= 2 then
      match b with
        | 1 -> " + "
        | 2 -> " - "
        | _ -> failwith "OOB"
    else
      match c with
        | 1 -> " + "
        | 2 -> " - "
        | 3 -> " * "
        | 4 -> " / "
        | _ -> failwith "OOB"

  member x.AritString calcLength =
    for i in 1..calcLength do
      StringCollector <- StringCollector + x.Pseudo() + x.Numeric()
    StringCollector <- StringCollector + x.Pseudo()
    StringCollector
end

let getQuestion (x) =
  
  let o = new Difficulty(x)
  let testrun =
    match x with
      | x when ((x >= 1) && (x <= 5)) -> o.AritString x
      | _ -> failwith "Index out of bounds"
      
  printfn "\n %s \n \n" testrun


let evalAnswer (question, answer) = 
  System.Console.Write((question:string) + " : " + (answer:string))
  
[<EntryPoint>]
let main(args) =
  if args.Length < 1 then
    System.Console.Write("You need to give a function as an argument: getQuestion() or evalAnswer(question,answer)")
    -1
  else
    let funcType = args.[0]
    if funcType = "getQuestion" then
      let input1 = args.[1]
      getQuestion (int(input1))
    elif funcType = "evalAnswer" then
      if args.Length <> 3 then
        System.Console.Write("evalAnswer needs the following arguments: [question] [answer]")
      else
        let input1 = args.[1]
        let input2 = args.[2]
        evalAnswer(input1, input2)
    0
