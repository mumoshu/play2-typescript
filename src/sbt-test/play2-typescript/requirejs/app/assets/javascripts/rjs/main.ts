import lib = module("./libs");

var Math = lib.Math;
var Text = lib.Text;

var x = "1+1=" + new Math.Add().apply(1, 2);
var y = "a+b=" + new Text.Add().apply("a", "b");

document.body.innerHTML = x + ", " + y;

