/// <reference path="lib.ts">
import lib = module("./lib")

var greeter = new lib.Greeter();

document.body.innerHTML = greeter.greet("mikoto");
