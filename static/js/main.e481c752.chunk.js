(this["webpackJsonpweb-app"]=this["webpackJsonpweb-app"]||[]).push([[0],{13:function(e,t,n){},14:function(e,t,n){},16:function(e,t,n){"use strict";n.r(t);var c=n(1),r=n.n(c),s=n(4),o=n.n(s),a=(n(13),n(5)),i=n(6),l=n(2),h=n(8),u=n(7),d=(n(14),n(0)),k=function(e){Object(h.a)(n,e);var t=Object(u.a)(n);function n(e){var c;return Object(a.a)(this,n),(c=t.call(this,e)).state={ready:!1,keywords:[],selected:[],checkboxes:[],link:null},c.handleCheck=c.handleCheck.bind(Object(l.a)(c)),c.handleClick=c.handleClick.bind(Object(l.a)(c)),c}return Object(i.a)(n,[{key:"componentDidMount",value:function(){var e=this;fetch("https://chartung17-resume-maker.herokuapp.com/help",{method:"GET"}).then((function(e){return e.json()}),(function(e){console.log(e)})).then((function(t){if(void 0!==t){if(200===t.status){var n=t.keywords;n=n.map((function(e){return e.replace(/[A-Z]/g,(function(e){return" "+e})).trim()}));for(var c=[],r=0;r<n.length;r++)c.push(Object(d.jsxs)("div",{className:"Checkbox",children:[Object(d.jsx)("input",{type:"checkbox",onChange:e.handleCheck(r),value:n[r]},r),n[r]]},r));e.setState({ready:!0,keywords:n,selected:n.map((function(e){return!1})),checkboxes:c})}}else console.log("Unknown error occurred")}))}},{key:"handleCheck",value:function(e){var t=this;return function(){var n=t.state.selected;n[e]=!n[e],t.setState({selected:n})}}},{key:"handleClick",value:function(){for(var e=this,t=this.state.keywords,n=this.state.selected,c=[],r=0;r<n.length;r++)n[r]&&c.push(t[r].replace(/ /g,""));fetch("https://chartung17-resume-maker.herokuapp.com/compile/"+c.join("-"),{method:"GET"}).then((function(e){return e.json()}),(function(e){console.log(e)})).then((function(t){if(void 0!==t){if(200===t.status){var n=t.link;e.setState({link:Object(d.jsx)("a",{href:n,target:"_blank",rel:"noreferrer",children:"Open Resume"})})}}else console.log("Unknown error occurred")}))}},{key:"render",value:function(){return Object(d.jsx)("div",{className:"App",children:Object(d.jsxs)("header",{className:"App-header",children:[Object(d.jsx)("h1",{children:"Resume Maker"}),Object(d.jsx)("p",{children:this.state.ready?"Please select all relevant keywords and then click the Compile button.":"Loading"}),Object(d.jsx)("div",{className:"checkboxes",children:this.state.checkboxes}),Object(d.jsx)("button",{onClick:this.handleClick,children:"Compile"}),this.state.link]})})}}]),n}(r.a.Component),p=function(e){e&&e instanceof Function&&n.e(3).then(n.bind(null,17)).then((function(t){var n=t.getCLS,c=t.getFID,r=t.getFCP,s=t.getLCP,o=t.getTTFB;n(e),c(e),r(e),s(e),o(e)}))};o.a.render(Object(d.jsx)(r.a.StrictMode,{children:Object(d.jsx)(k,{})}),document.getElementById("root")),p()}},[[16,1,2]]]);
//# sourceMappingURL=main.e481c752.chunk.js.map