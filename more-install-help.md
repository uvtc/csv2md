# More Install Help

Clojure programs are often distributed as jar files (just like Java
programs), but some (like this one) are just Clojure code that is run
almost like any other script on your system.

Before doing anything else, make sure you've got Leiningen installed.
Check its install by running:

    lein version

Once that succeeds, you'll need to configure `lein` to use the
lein-exec plug-in. Edit (creating it if you have to)
~/.lein/profiles.clj.  If you're creating it from scratch, make it
look like this:

~~~clojure
{:user {:plugins [[lein-exec "0.3.4"]]}}
~~~

(that's the latest version of lein-exec as of 2014-08-06).

The final step is to:

 #. grab lein-exec's tiny [lein-exec
    script](https://raw.githubusercontent.com/kumarshantanu/lein-exec/master/lein-exec),
 #. save it to somewhere on your $PATH (for example, to your ~/bin), and
 #. make sure it's set to executable (`chmod +x ~/bin/lein-exec`)

Now just put csv2md.clj (or any other script in Clojure that makes use
of lein-exec) into your ~/bin, set it to executable, and run it in the
usual fashion:

    csv2md.clj foo.csv > foo.md
