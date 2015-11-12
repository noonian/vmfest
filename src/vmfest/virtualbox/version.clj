(ns vmfest.virtualbox.version)

(defn vbox-binding []
  (try (import 'org.mozilla.interfaces.IVirtualBox)
       :xpcom
       (catch Exception e
         (try
           (import 'org.virtualbox_5_0.jaxws.IVirtualBoxCreateHardDisk)
           :ws
           (catch Exception e
             :error)))))

(defn xpcom? [] (= (vbox-binding) :xpcom))

(defmacro evaluate-when [b & body]
  (when (= (vbox-binding) b)
    `(do ~@body)))
