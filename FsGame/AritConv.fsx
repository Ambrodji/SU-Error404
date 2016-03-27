// return = "* - 5 6 7"


let TestInput = "5 ( 5 - 6 ) * 7"

printfn "%A" (TestInput.Split [|' '|])

let test = TestInput.Split [|' '|]
let ArrayList = test 

let mega(list) =
  let mutable Collector = list
  let mutable i = 0
  let mutable firstIn = 0
  let mutable lastIn = 0
  while i < (Array.length list) do
    printfn "%A" i

    if list.[i] = "(" then
      firstIn <- i

    if list.[i] = ")" then
      lastIn <- i


    //Count and print
    i <- i + 1
    printfn "Coll : %A \n" Collector
    printfn "First Index : %A \n" firstIn
    printfn "Last Index : %A \n" lastIn

  while 
    
  
  Collector


let final = mega(ArrayList)

let fin' = final |> Array.filter ((<>)"rm")
  

printfn "%A" (fin')


////////////////////
