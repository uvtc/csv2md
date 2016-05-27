# csv2md.clj

Turns this:

~~~
aquamarine,8,circle
green,9,ellipsoid
blue,10,curvy
~~~

into:

~~~
------------  ----  -----------
aquamarine    8     circle
green         9     ellipsoid
blue          10    curvy
------------  ----  -----------
~~~

(You'll have to move that first table delimiter line down if
the first line of your csv is intended to be a heading.)


## What is it?

It's a script. It happens to be written in
[Clojure](http://clojure.org/).


## Prerequisites

You'll need to have already installed:

  * [Java](http://openjdk.java.net/) (known to work with openjdk-{7,8}-jre)
  * [inlein](http://inlein.org/)


## Compatibility

The author has not given even a passing thought to running this
program on any OS other than GNU/Linux.


## Install

Just put csv2md.clj anywhere in your $PATH, and make sure it's
executable (`chmod +x csv2md.clj`).


## Usage

    csv2md.clj foo.clj > foo.md


## License

GPLv3. See [COPYING.txt](COPYING.txt) for details.

Copyright 2014, 2015, 2016 John Gabriele <jgabriele@fastmail.fm>
