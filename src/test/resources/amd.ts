/// <reference path="lib.ts">
import lib = require("./lib")

var greeter = new lib.Greeter();

document.body.innerHTML = greeter.greet("mikoto");
