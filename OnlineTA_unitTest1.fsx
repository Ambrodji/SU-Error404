open OnlineTA_TrueFalseGame

let test x = if x then printf "." else printf "#"

let fn (arg:obj) = 
    match arg with
    | :? string as str -> true
    | _ -> false

test (getQuestion(1) = true)
//test (evalAnswer(question,answer) = Cake3)