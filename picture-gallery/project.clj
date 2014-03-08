(defproject picture_gallery "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.4"]
                 [ring-server "0.3.0"]
                 [postgresql/postgresql "9.1-901.jdbc4"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [lib-noir "0.7.6"]
                 [org.clojure/tools.reader "0.7.10"]
                 [org.clojure/clojurescript "0.0-2173"]
                 [domina "1.0.2"]
                 [cljs-ajax "0.2.3"]
                 [ring-middleware-format "0.3.1"]]
  :plugins [[lein-ring "0.8.7"]
            [lein-cljsbuild "1.0.2"]]
  :ring {:handler picture_gallery.handler/app
         :init picture_gallery.handler/init
         :destroy picture_gallery.handler/destroy}
  :cljsbuild
  {:builds
   [{:source-paths ["src-cljs"],
     :compiler
     {:pretty-print true,
      :output-to "resources/public/js/gallery-cljs.js"}}]}
  :aot :all
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.0"]]}})
