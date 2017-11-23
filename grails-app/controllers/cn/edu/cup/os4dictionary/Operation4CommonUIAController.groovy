package cn.edu.cup.os4dictionary

import cn.edu.cup.dictionary.CommonUIA

class Operation4CommonUIAController {

    def index() {
        if (params.commonUIA) {
            def c = CommonUIA.get(params.commonUIA)
            session.commonUIA = c
        } else {
            session.removeAttribute("commonUIA")
        }
    }

}
