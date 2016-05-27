module functionTestLand

let hint = ", \"hint\": \"Test hint\""

let rnd = System.Random()

/// Return a random bool from minNum to maxNum
let foo minNum maxNum = 
    let first = rnd.Next(minNum, maxNum)
    first

/// Return a random bool value
let booly () =
  let first = rnd.Next(0, 2)
  let mutable boolVal = true 
  if first = 0 then boolVal <- true else boolVal <- false
  boolVal

let mutable lengthBe = 0

///Generates a lemma and calculates answer
let getQuestion (x) = 
  match x with 
  |1 -> let array1 = [| (foo 1 100); (foo 1 100) |]
        let array2 = [| (booly ()); (booly ()) |]
        let answerCheck = if array1.[0] < array1.[1] then
                            array2.[0] else array2.[1]
        
        ("{ \"question\": \"if " 
          + string(array1.[0]) + " < " + string(array1.[1]) 
          + " then " + string(array2.[0]) + " else " + string(array2.[1]) 
          + "\", \"answer\": \"" + string(answerCheck) + "\", \"choices\": [\"True\", \"False\"], \"hint\": \"Just Do It\" }")

  |2 -> let array1 = Array.init 10 (fun x -> foo 1 10)
        let array2 = [| (booly ()); (booly ()) |]

        /// Checks if answer is correct
        let answerCheck =
          let mutable count = 0
          for i in 0..(array1.Length-1) do
            if (array1.[i] % (array1.[0])) = 0 then
              count <- count + array1.[i]
          if count < (array1.[1] + array1.[2]) then array2.[0] else array2.[1]

        /// Writes string for user to read
        let mutable arrString = ""
        for i in 0..(array1.Length-2) do
          arrString <- arrString + (string(array1.[i]) + ";")

        ("{ \"question\": \"let array1 = [|" + arrString + string(array1.[9]) + "|]\nlet mutable count = 0\n\n" 
                 + "for i in 1..(array1.Length-1) do\n  if array1.[i] % " 
                 + string(array1.[0]) + " = 0 then\n    count <- count + array1.[i] \n  if count < ("
                 + string(array1.[1]) + " + " 
                 + string(array1.[2]) + ") then " + string(array2.[0]) + " else " 
                 + string(array2.[1]) + "\", \"answer\": \"" + string(answerCheck) + "\" }")
//  | 3 ->  let array1 = [| for i in 1 .. 10 -> (foo 1 9) * (foo 1 9) |]
//          let randIndex1 = foo 0 (array1.Length-1)
//          let answer1 = array1.[randIndex1]
//          printfn "{ \"question\": \"let array1 = %A\nIndex %d = ?\", \"answer\": \"%d\"%s }" 
//            array1 randIndex1 answer1 hint
//  | 4 ->  let mutable array1 = Array2D.create 10 10 0
//          for i in 0..9 do
//            for j in 0..9 do
//              array1.[i,j] <- (foo 1 9)
//          let index2D1 = (foo 1 9)
//          let index2D2 = (foo 1 9)
//          printfn "{ \"question\": \"let array1 = \n%A\nIndex[%d,%d] = ?\", \"answer\": \"%d\"%s }" 
//            array1 index2D1 index2D2 array1.[index2D1,index2D2] hint
//  | 5 ->  let array1 = [|  for i in 1 .. 5 -> (foo 1 9) |]
//          let array2 = [| for i in 1 .. 5 -> (foo 1 9) |]
//          let index1D1 = (foo 0 4)
//          let index1D2 = (foo 0 4)
//          let arrayResult = (array1.[index1D1]) + (array2.[index1D2])
//          printfn "{ \"question\": \"let array1 = %A\nlet array2 = %A\nlet addition = array1.[%d]
//  + array2.[%d]\naddition = ?\", \"answer\": \"%d\"%s }" array1 array2 index1D1 index1D2 arrayResult hint
//  | 6 ->  let array1 = [| for i in 1 .. (foo 5 9) -> (foo 1 9) |]
//          let randStep = (foo 1 4)
//          let mutable count = 0
//          for i in 0..randStep..(array1.Length - 1) do
//            count <- count + array1.[i]
//          printfn "{ \"question\": \"let array1 = %A\nlet mutable count = 0\nfor i in 0..%d..(array1.Length - 1) do
//  count <- count + array1.[i]\ncount = ?\", \"answer\": \"%d\"%s }" array1 randStep count hint
//  | 7 ->  let array1 = [|0;1;2;3;4|]
//          let rand1 = foo 0 4
//          printfn "{ \"question\": \"let array1 = %A \nIndex %d = ?\", \"answer\": \"%A\"%s }"
//            array1 rand1 array1.[rand1] hint
  |_ -> lengthBe <- (x-1)
        ("{ \"question\": \"1\", \"answer\": \"2\", \"choices\": [\"True\", \"False\"], \"hint\": \"Just Do It\" }")

//let evalAnswer (question, answer) = 
//  Console.WriteLine("Not used")