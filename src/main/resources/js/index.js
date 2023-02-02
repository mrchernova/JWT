// ReactDOM.render(<div>
//     <h1>HI</h1>
//     <h2>***</h2>
// </div>, document.getElementById("app"))


ReactDOM.render(React.createElement('input',{
    placeholder:"help text",
    onClick:()=>console.log("Clicked")

}), document.getElementById("app"))