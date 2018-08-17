(defproject
  {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[binaryage/devtools "0.9.10"]
                 [thheller/shadow-cljs "2.5.1"]
                 {{#om}}
                 [com.tiensonqin/om "1.0.0-beta2-SNAPSHOT"]
                 {{/om}}
                 {{#rum}}
                 [ua.modnakasta/rum "0.11.0-2"]
                 [org.roman01la/citrus "3.0.0"
                  :exclusions [rum]]
                 {{/rum}}
                 {{#reagent}}
                 [reagent "0.8.0-alpha2"]
                 {{/reagent}}]
  :source-paths ["src"])
