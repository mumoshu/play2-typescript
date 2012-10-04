
import sayings = module("./sayings")

var greeter = new sayings.sayings_module.Greeter("world");
document.body.innerHTML = greeter.greet();
