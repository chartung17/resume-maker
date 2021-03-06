import React from 'react';
import './App.css';

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      ready: false,
      keywords: [],
      selected: [],
      checkboxes: [],
      link: null
    }
    this.handleCheck = this.handleCheck.bind(this);
    this.handleClick = this.handleClick.bind(this);
  }

  componentDidMount() {
    fetch("https://chartung17-resume-maker.herokuapp.com/help", {
      method: 'GET'
    })
    .then(res => {
      return res.json();
    }, err => {
      // Print the error if there is one.
      console.log(err);
    }).then(result => {
      if (result === undefined) {
        console.log('Unknown error occurred');
        return;
      } else if (result['status'] === 200) {
        let keywords = result['keywords'];
        keywords = keywords.map(s => s.replace(/[A-Z]/g, c => ' ' + c).trim());
        let checkboxes = [];
        for (let i = 0; i < keywords.length; i++) {
          checkboxes.push(
            <div className="Checkbox" key={i}>
            <input type={'checkbox'} key={i} onChange={this.handleCheck(i)} value={keywords[i]}/>
            {keywords[i]}
            </div>
          );
        }
        this.setState({
          ready: true,
          keywords: keywords,
          selected: keywords.map(s => false),
          checkboxes: checkboxes
        });
      }
    });
  }

  handleCheck(i) {
    return () => {
      let selected = this.state.selected;
      selected[i] = !selected[i];
      this.setState({
        selected: selected
      });
    }
  }

  handleClick() {
    let keywords = this.state.keywords;
    let selected = this.state.selected;
    let selectedKeywords = [];
    for (let i = 0; i < selected.length; i++) {
      if (selected[i]) {
        selectedKeywords.push(keywords[i].replace(/ /g, ''));
      }
    }
    fetch("https://chartung17-resume-maker.herokuapp.com/compile/" + selectedKeywords.join('-'), {
      method: 'GET'
    })
    .then(res => {
      return res.json();
    }, err => {
      // Print the error if there is one.
      console.log(err);
    }).then(result => {
      if (result === undefined) {
        console.log('Unknown error occurred');
        return;
      } else if (result['status'] === 200) {
        let link = result['link'];
        this.setState({
          link: <a href={link} target="_blank" rel="noreferrer">Open Resume</a>
        });
      }
    });
  }

  render() {
    return (
      <div className="App">
      <header className="App-header">
      <h1>Resume Maker</h1>
      <p>{this.state.ready ? 'Please select all relevant keywords and then click the Compile button.' : 'Loading'}</p>
      <div className="checkboxes">{this.state.checkboxes}</div>
      <button onClick={this.handleClick}>Compile</button>
      {this.state.link}
      </header>
      </div>
    );
  }
}
