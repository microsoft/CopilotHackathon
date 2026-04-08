let gridSize = 4;
let numPlayers = 1;
let cards = [];
let flippedCards = [];
let matchedPairs = 0;
let totalPairs = 0;
let moves = 0;
let currentPlayer = 1;
let scores = { 1: 0, 2: 0 };
let lockBoard = false;

async function startGame() {
    gridSize = parseInt(document.getElementById('grid-size').value);
    numPlayers = parseInt(document.getElementById('num-players').value);
    totalPairs = (gridSize * gridSize) / 2;

    document.getElementById('setup-screen').classList.add('hidden');
    document.getElementById('game-screen').classList.remove('hidden');

    if (numPlayers === 2) {
        document.getElementById('player2-score').classList.remove('hidden');
    }

    resetScoreboard();
    await loadPokemon();
}

async function loadPokemon() {
    const grid = document.getElementById('grid');
    grid.innerHTML = '<p style="text-align:center;grid-column:1/-1;">Loading Pokémon...</p>';
    grid.className = `grid-${gridSize}`;

    // Pick random pokemon IDs (1-151, Gen 1)
    const ids = [];
    while (ids.length < totalPairs) {
        const id = Math.floor(Math.random() * 151) + 1;
        if (!ids.includes(id)) ids.push(id);
    }

    try {
        const pokemonData = await Promise.all(
            ids.map(async id => {
                const res = await fetch(`https://pokeapi.co/api/v2/pokemon/${id}`);
                const data = await res.json();
                return {
                    id: data.id,
                    name: data.name,
                    image: data.sprites.other['official-artwork'].front_default
                        || data.sprites.front_default
                };
            })
        );

        // Create pairs and shuffle
        cards = [...pokemonData, ...pokemonData]
            .map((pokemon, index) => ({ ...pokemon, uniqueId: index }));
        shuffle(cards);
        renderGrid();
    } catch (err) {
        grid.innerHTML = '<p style="text-align:center;grid-column:1/-1;color:#e94560;">Failed to load Pokémon. Check your connection.</p>';
    }
}

function shuffle(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}

function renderGrid() {
    const grid = document.getElementById('grid');
    grid.innerHTML = '';

    cards.forEach((card, index) => {
        const el = document.createElement('div');
        el.className = 'card';
        el.dataset.index = index;
        el.innerHTML = `
            <div class="card-inner">
                <div class="card-front"></div>
                <div class="card-back">
                    <img src="${escapeAttr(card.image)}" alt="${escapeAttr(card.name)}" loading="lazy">
                </div>
            </div>
        `;
        el.addEventListener('click', () => flipCard(index, el));
        grid.appendChild(el);
    });
}

function flipCard(index, el) {
    if (lockBoard) return;
    if (el.classList.contains('flipped') || el.classList.contains('matched')) return;
    if (flippedCards.length >= 2) return;

    el.classList.add('flipped');
    flippedCards.push({ index, el, card: cards[index] });

    if (flippedCards.length === 2) {
        moves++;
        document.getElementById('moves-counter').textContent = `Moves: ${moves}`;
        checkMatch();
    }
}

function checkMatch() {
    const [first, second] = flippedCards;
    const isMatch = first.card.id === second.card.id && first.index !== second.index;

    if (isMatch) {
        first.el.classList.add('matched');
        second.el.classList.add('matched');
        matchedPairs++;
        scores[currentPlayer]++;
        updateScoreboard();
        flippedCards = [];

        if (matchedPairs === totalPairs) {
            setTimeout(showWin, 600);
        }
    } else {
        lockBoard = true;
        setTimeout(() => {
            first.el.classList.remove('flipped');
            second.el.classList.remove('flipped');
            flippedCards = [];
            lockBoard = false;

            if (numPlayers === 2) {
                currentPlayer = currentPlayer === 1 ? 2 : 1;
                updateScoreboard();
            }
        }, 800);
    }
}

function updateScoreboard() {
    document.querySelector('#player1-score .player-points').textContent = `${scores[1]} pairs`;
    document.querySelector('#player2-score .player-points').textContent = `${scores[2]} pairs`;

    document.getElementById('player1-score').classList.toggle('active-player', currentPlayer === 1);
    document.getElementById('player2-score').classList.toggle('active-player', currentPlayer === 2);
}

function resetScoreboard() {
    scores = { 1: 0, 2: 0 };
    moves = 0;
    matchedPairs = 0;
    currentPlayer = 1;
    flippedCards = [];
    lockBoard = false;
    document.getElementById('moves-counter').textContent = 'Moves: 0';
    updateScoreboard();
}

function showWin() {
    const modal = document.getElementById('win-modal');
    const msg = document.getElementById('win-message');
    const details = document.getElementById('win-details');

    if (numPlayers === 1) {
        msg.textContent = 'You Win!';
        details.textContent = `Completed in ${moves} moves!`;
    } else {
        if (scores[1] > scores[2]) {
            msg.textContent = 'Player 1 Wins!';
        } else if (scores[2] > scores[1]) {
            msg.textContent = 'Player 2 Wins!';
        } else {
            msg.textContent = "It's a Tie!";
        }
        details.textContent = `Player 1: ${scores[1]} pairs | Player 2: ${scores[2]} pairs | ${moves} total moves`;
    }

    modal.classList.remove('hidden');
}

function resetGame() {
    document.getElementById('win-modal').classList.add('hidden');
    document.getElementById('game-screen').classList.add('hidden');
    document.getElementById('setup-screen').classList.remove('hidden');
    document.getElementById('player2-score').classList.add('hidden');
}

function escapeAttr(str) {
    const div = document.createElement('div');
    div.textContent = str;
    return div.innerHTML;
}
