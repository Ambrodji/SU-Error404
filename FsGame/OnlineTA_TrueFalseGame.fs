open System

/// Types that work with this program

let rnd = System.Random()

let foo minNum maxNum = 
    let first = rnd.Next(minNum, maxNum)
    first

let booly () =
  let first = rnd.Next(0, 2)
  let mutable boolVal = true 
  if first = 0 then boolVal <- true else boolVal <- false
  boolVal  

///Generates a lemma
let getQuestion (x) = 
  match x with 
  |1 -> let array1 = [| (foo 1 100); (foo 1 100) |]
        let array2 = [| (booly ()); (booly ()) |]
        let answerCheck = if array1.[0] < array1.[1] then
                            array2.[0] else array2.[1]
        
        Console.WriteLine("{ \"question\": \"if " 
          + string(array1.[0]) + " < " + string(array1.[1]) 
          + " then " + string(array2.[0]) + " else " + string(array2.[1]) 
          + "\", \"answer\": \"" + string(answerCheck) + "\" }")

  |2 -> let myArray = Array.init 10 (fun x -> foo 1 10)
        let array2 = [| (booly ()); (booly ()) |]

        let answerCheck =
          let mutable count = 0
          for i in 0..(myArray.Length-1) do
            if (myArray.[i] % (myArray.[0])) = 0 then
              count <- count + myArray.[i]
              Console.WriteLine(count)
          if count < (myArray.[1] + myArray.[2]) then array2.[0] else array2.[1]

        Console.WriteLine("{ \"question\": \"let myArray = [|" + string(myArray.[0]) + ";"
                 + string(myArray.[1]) + ";" + string(myArray.[2]) + ";"
                 + string(myArray.[3]) + ";" + string(myArray.[4]) + ";"
                 + string(myArray.[5]) + ";" + string(myArray.[6]) + ";"
                 + string(myArray.[7]) + ";" + string(myArray.[8]) + ";"
                 + string(myArray.[9]) + "|]\nlet mutable count = 0\n\n
                 for i in 1..(myArray.Length-1) do\n  if (string(myArray.[i]) %" 
                 + string(myArray.[0]) + ") = 0 then\n    count <- count + myArray.[i] \n  if count < ("
                 + string(myArray.[1]) + " + " 
                 + string(myArray.[2]) + ") then " + string(array2.[0]) + " else " 
                 + string(array2.[1]) + ",\" \"answer\": \"" + string(answerCheck) + "\" })")
//  |3 -> Console.WriteLine("3")
  |4 -> Console.WriteLine("4")
  |5 -> Console.WriteLine("5")
  |_ -> ()

///Evaluates lemma against given answer versus calculated answer
let evalAnswer (question, answer) = 
  Console.WriteLine("Not used")
    

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