import {makeAutoObservable} from "mobx";

export default class FormStore{

    constructor() {
        this._forms = []
        this._page = 1
        this._totalCount = 0
        this._limit = 5
        makeAutoObservable(this)
    }

    get forms() {
        return this._forms;
    }

    setForms(forms) {
        this._forms = forms;
    }

    get page() {
        return this._page;
    }

    setPage(page) {
        this._page = page;
    }

    get totalCount() {
        return this._totalCount;
    }

    setTotalCount(totalCount) {
        this._totalCount = totalCount;
    }

    get limit() {
        return this._limit;
    }

    setLimit(limit) {
        this._limit = limit;
    }
}