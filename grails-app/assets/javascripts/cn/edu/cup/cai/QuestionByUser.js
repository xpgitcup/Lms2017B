function prepareToImportQuestion() {
    operation4QuestionTypeDiv.tabs("select", "数据编辑")
    ajaxRun("operation4Question/prepareToImport", 0, "editQuestionDiv");
}