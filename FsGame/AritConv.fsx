// return = "+ 5 * 7 - 5 6"


let TestInput = "5 + ( 5 - 6 ) * 7"

printfn "%A" (TestInput.Split [|' '|])

let test = TestInput.Split [|' '|]
let ArrayList = test 

let mega(list) =
  let mutable Collector = list
  let mutable i = 0
  let mutable firstIn = 0
  let mutable lastIn = 0
  let mutable tmp = ""
  while i < (Array.length list) do
    printfn "%A" i

    if list.[i] = "(" then
      firstIn <- i
      Collector.[i] <- "rm"

    if list.[i] = ")" then
      lastIn <- i
      Collector.[i] <- "rm"

    
    //Count and print
    i <- i + 1
    printfn "Coll : %A \n" Collector
    printfn "First Index : %A \n" firstIn
    printfn "Last Index : %A \n" lastIn

  let mutable index = firstIn
  while index < lastIn do
    match list.[index] with
      | "+" -> tmp <- "+"
      | "*" -> tmp <- "*"
      | "-" -> tmp <- "-"
               Collector <- Collector |> Array.filter ((<>)"-")
      | "/" -> tmp <- "/"
      | _ -> ()
      
    index <- index + 1

  let mutable StartColl = Collector.[0..firstIn]
  let mutable EndColl = Collector.[firstIn+1..(Collector.Length-1)]
  
  //Collector <- Array.append [|tmp|] Collector

  Collector <- Array.append StartColl [|tmp|]
  Collector <- Array.append Collector EndColl
  printfn "%A" tmp
  printfn "end"
  printfn "%A %A" StartColl EndColl
  Collector

//return megalist and filter rm (remove) from it
let final = mega(ArrayList)

let fin' = final |> Array.filter ((<>)"rm")
  

printfn "%A" (fin')


////////////////////
