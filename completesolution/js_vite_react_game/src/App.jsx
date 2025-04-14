import React, { useState } from 'react';
import './App.css';
import enemy from './ascii-art/enemy.atxt';
import ship from './ascii-art/ship.atxt';
import ammo from './ascii-art/ammo.atxt';

function App() {
  // State to hold an array of enemies with their coordinates
  const [enemies, setEnemies] = useState([
    { id: 1, x: 10, y: 10 },
    { id: 2, x: 30, y: 10 },
    { id: 3, x: 50, y: 10 },
  ]);

  // State to hold the ship's coordinates
  const [shipPosition, setShipPosition] = useState({ x: 30, y: 64 });

  // State to hold ammo positions
  const [ammoPositions, setAmmoPositions] = useState([
    { id: 1, x: 10, y: 50 }
  ]);

  return (
    <>
      <div
        style={{
          border: '1px solid black',
          width: '640px',
          height: '800px',
          overflow: 'auto',
          position: 'relative',
        }}
      >
        {/* Render enemies dynamically */}
        {enemies.map((enemyObj) => (
          <div
            key={enemyObj.id}
            dangerouslySetInnerHTML={{ __html: enemy }}
            style={{
              position: 'absolute',
              left: `${enemyObj.x * 10}px`, // Apply scale factor here
              top: `${enemyObj.y * 10}px`, // Apply scale factor here
              display: 'inline-block',
            }}
          />
        ))}

        {/* Render ammo dynamically */}
        {ammoPositions.map((ammoObj) => (
          <div
            key={ammoObj.id}
            dangerouslySetInnerHTML={{ __html: ammo }}
            style={{
              position: 'absolute',
              left: `${ammoObj.x * 10}px`, // Apply scale factor here
              top: `${ammoObj.y * 10}px`, // Apply scale factor here
              display: 'inline-block',
            }}
          />
        ))}

        {/* Render the ship */}
        <div
          dangerouslySetInnerHTML={{ __html: ship }}
          style={{
            position: 'absolute',
            left: `${shipPosition.x * 10}px`, // Apply scale factor here
            top: `${shipPosition.y * 10}px`, // Apply scale factor here
            display: 'inline-block',
          }}
        />
      </div>
      <button
        onClick={() => {
          const enemyWidth = 10; // Width of the enemy (from ASCII art)
          const enemyHeight = 10; // Height of the enemy (from ASCII art)
          const ammoWidth = 3; // Width of the ammo
          const ammoHeight = 3; // Height of the ammo

          // Update enemies' positions
          const updatedEnemies = enemies.map((enemyObj) => ({
            ...enemyObj,
            y: enemyObj.y + 1,
          }));
          setEnemies(updatedEnemies);

          // Update ammo positions
          const updatedAmmo = ammoPositions.map((ammoObj) => ({
            ...ammoObj,
            y: ammoObj.y - 1,
          }));
          setAmmoPositions(updatedAmmo);

          // Check for collisions using bounding box detection
          const remainingEnemies = updatedEnemies.filter((enemy) => {
            const isHit = updatedAmmo.some((ammo) => {
              return (
                ammo.x < enemy.x + enemyWidth &&
                ammo.x + ammoWidth > enemy.x &&
                ammo.y < enemy.y + enemyHeight &&
                ammo.y + ammoHeight > enemy.y
              );
            });
            console.log('isHit', isHit);
            return !isHit; // Keep enemies that are not hit
          });

          const remainingAmmo = updatedAmmo.filter((ammo) => {
            const hasHit = updatedEnemies.some((enemy) => {
              return (
                ammo.x < enemy.x + enemyWidth &&
                ammo.x + ammoWidth > enemy.x &&
                ammo.y < enemy.y + enemyHeight &&
                ammo.y + ammoHeight > enemy.y
              );
            });
            return !hasHit; // Keep ammo that hasn't hit an enemy
          });

          // Update state with remaining enemies and ammo
          setEnemies(remainingEnemies);
          setAmmoPositions(remainingAmmo);
        }}
      >
        Tick
      </button>
    </>
  );
}

export default App;
