import lib = module("./libs");
import utils = module("./libs/utils");
import utils2 = module("libs2/utils2");

var Math = lib.Math;
var Text = utils.Text;
//var Text = lib.Text;

var x = "1+1=" + new Math.Add().apply(1, 2);
var y = "a+b=" + new Text.Add().apply("a", "b");

document.body.innerHTML = x + ", " + y;

